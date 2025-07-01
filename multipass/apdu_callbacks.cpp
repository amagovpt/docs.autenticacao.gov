#include <cstdio>
#include <eidlib.h>
#include <eidlibException.h>
#include <format>
#include <iostream>
#include <sstream>
#include <thread>
#include <time.h>
#include <unistd.h>

#include <PCSC/winscard.h>
#include <PCSC/wintypes.h>

using namespace eIDMW;

class SimplePCSCCallbacks {
private:
	SCARDCONTEXT m_hContext;
	std::map<PTEID_CardHandle, SCARDHANDLE> m_handles;
	PTEID_CardHandle m_nextHandle;

public:
	SimplePCSCCallbacks() : m_hContext(0), m_nextHandle(1) {}

	static PTEID_CallbackResult EstablishContext(void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		LONG lRet = SCardEstablishContext(SCARD_SCOPE_USER, NULL, NULL, &self->m_hContext);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			return PTEID_CALLBACK_OK;
		case SCARD_E_NO_SERVICE:
		case SCARD_E_SERVICE_STOPPED:
			return PTEID_CALLBACK_ERR_NO_READER;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_GENERIC;
		}
	}

	static PTEID_CallbackResult ReleaseContext(void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		if (self->m_hContext != 0) {
			SCardReleaseContext(self->m_hContext);
			self->m_hContext = 0;
		}
		return PTEID_CALLBACK_OK;
	}

	static PTEID_CallbackResult ListReaders(unsigned char *buffer, unsigned long *bufferSize, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self || !buffer || !bufferSize) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		DWORD dwReadersLen = *bufferSize;
		LONG lRet = SCardListReaders(self->m_hContext, NULL, (char *)buffer, &dwReadersLen);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			*bufferSize = dwReadersLen;
			return PTEID_CALLBACK_OK;
		case SCARD_E_INSUFFICIENT_BUFFER:
			*bufferSize = dwReadersLen;
			return PTEID_CALLBACK_ERR_BUFFER_TOO_SMALL;
		case SCARD_E_NO_READERS_AVAILABLE:
			if (*bufferSize >= 2) {
				buffer[0] = 0;
				buffer[1] = 0;
				*bufferSize = 2;
				return PTEID_CALLBACK_OK;
			} else {
				*bufferSize = 2;
				return PTEID_CALLBACK_ERR_BUFFER_TOO_SMALL;
			}
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_GENERIC;
		}
	}

	static PTEID_CallbackResult CardPresentInReader(const char *csReader, void *context, bool *cardPresent) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self || !csReader || !cardPresent) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		SCARD_READERSTATE readerState;
		readerState.szReader = csReader;
		readerState.dwCurrentState = SCARD_STATE_UNAWARE;

		LONG lRet = SCardGetStatusChange(self->m_hContext, 0, &readerState, 1);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			*cardPresent = (readerState.dwEventState & SCARD_STATE_PRESENT) != 0;
			return PTEID_CALLBACK_OK;
		case SCARD_E_UNKNOWN_READER:
			return PTEID_CALLBACK_ERR_NO_READER;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_COMM_ERROR;
		}
	}

	static PTEID_CallbackResult Connect(const char *csReader, PTEID_CardHandle *outHandle,
										PTEID_CardProtocol *outProtocol, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self || !csReader || !outHandle || !outProtocol) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		SCARDHANDLE hCard;
		DWORD dwActiveProtocol;

		LONG lRet = SCardConnect(self->m_hContext, csReader, SCARD_SHARE_SHARED, SCARD_PROTOCOL_T0 | SCARD_PROTOCOL_T1,
								 &hCard, &dwActiveProtocol);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			*outHandle = self->m_nextHandle++;
			*outProtocol = (dwActiveProtocol == SCARD_PROTOCOL_T0) ? PTEID_CardProtocol::T0 : PTEID_CardProtocol::T1;
			self->m_handles[*outHandle] = hCard;
			return PTEID_CALLBACK_OK;
		case SCARD_E_NO_SMARTCARD:
			return PTEID_CALLBACK_ERR_NO_CARD;
		case SCARD_E_UNKNOWN_READER:
			return PTEID_CALLBACK_ERR_NO_READER;
		case SCARD_E_SHARING_VIOLATION:
			return PTEID_CALLBACK_ERR_ACCESS_DENIED;
		case SCARD_W_UNRESPONSIVE_CARD:
		case SCARD_W_UNPOWERED_CARD:
			return PTEID_CALLBACK_ERR_UNRESPONSIVE;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_COMM_ERROR;
		}
	}

	static PTEID_CallbackResult Disconnect(PTEID_CardHandle handle, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		auto it = self->m_handles.find(handle);
		if (it == self->m_handles.end()) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		LONG lRet = SCardDisconnect(it->second, SCARD_LEAVE_CARD);
		self->m_handles.erase(it);

		return PTEID_CALLBACK_OK;
	}

	static PTEID_CallbackResult Transmit(PTEID_CardHandle handle, const unsigned char *cmdData, unsigned long cmdLength,
										 unsigned char *responseBuffer, unsigned long *respBufferSize, long *plRetVal,
										 const void *pSendPci, void *pRecvPci, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self || !cmdData || !responseBuffer || !respBufferSize) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		auto it = self->m_handles.find(handle);
		if (it == self->m_handles.end()) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		DWORD dwRecvLen = *respBufferSize;
		const SCARD_IO_REQUEST *pioSendPci = (const SCARD_IO_REQUEST *)pSendPci;

		SCARD_IO_REQUEST defaultPci = {SCARD_PROTOCOL_T1, sizeof(SCARD_IO_REQUEST)};
		if (!pioSendPci) {
			pioSendPci = &defaultPci;
		}

		LONG lRet = SCardTransmit(it->second, pioSendPci, cmdData, cmdLength, NULL, responseBuffer, &dwRecvLen);

		if (plRetVal) {
			*plRetVal = lRet;
		}

		switch (lRet) {
		case SCARD_S_SUCCESS:
			*respBufferSize = dwRecvLen;
			return PTEID_CALLBACK_OK;
		case SCARD_W_REMOVED_CARD:
			return PTEID_CALLBACK_ERR_CARD_REMOVED;
		case SCARD_E_INSUFFICIENT_BUFFER:
			return PTEID_CALLBACK_ERR_BUFFER_TOO_SMALL;
		case SCARD_W_UNRESPONSIVE_CARD:
			return PTEID_CALLBACK_ERR_UNRESPONSIVE;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_COMM_ERROR;
		}
	}

	static PTEID_CallbackResult StatusWithATR(PTEID_CardHandle handle, unsigned char *buffer, unsigned long *bufferSize,
											  void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self || !buffer || !bufferSize) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		auto it = self->m_handles.find(handle);
		if (it == self->m_handles.end()) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		DWORD dwReaderLen = 0;
		DWORD dwState, dwProtocol;
		DWORD dwATRLen = *bufferSize;

		LONG lRet = SCardStatus(it->second, NULL, &dwReaderLen, &dwState, &dwProtocol, buffer, &dwATRLen);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			*bufferSize = dwATRLen;
			return PTEID_CALLBACK_OK;
		case SCARD_W_REMOVED_CARD:
			return PTEID_CALLBACK_ERR_CARD_REMOVED;
		case SCARD_E_INSUFFICIENT_BUFFER:
			*bufferSize = dwATRLen;
			return PTEID_CALLBACK_ERR_BUFFER_TOO_SMALL;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_COMM_ERROR;
		}
	}

	static PTEID_CallbackResult BeginTransaction(PTEID_CardHandle handle, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		auto it = self->m_handles.find(handle);
		if (it == self->m_handles.end()) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		LONG lRet = SCardBeginTransaction(it->second);

		switch (lRet) {
		case SCARD_S_SUCCESS:
			return PTEID_CALLBACK_OK;
		case SCARD_E_SHARING_VIOLATION:
			return PTEID_CALLBACK_ERR_ACCESS_DENIED;
		case SCARD_W_REMOVED_CARD:
			return PTEID_CALLBACK_ERR_CARD_REMOVED;
		case SCARD_E_INVALID_PARAMETER:
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		default:
			return PTEID_CALLBACK_ERR_COMM_ERROR;
		}
	}

	static PTEID_CallbackResult EndTransaction(PTEID_CardHandle handle, void *context) {
		SimplePCSCCallbacks *self = static_cast<SimplePCSCCallbacks *>(context);
		if (!self) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		auto it = self->m_handles.find(handle);
		if (it == self->m_handles.end()) {
			return PTEID_CALLBACK_ERR_INVALID_PARAM;
		}

		SCardEndTransaction(it->second, SCARD_LEAVE_CARD);
		return PTEID_CALLBACK_OK;
	}

	static PTEID_CallbackResult Recover(PTEID_CardHandle handle, unsigned long *pulLockCount, void *context) {
		return PTEID_CALLBACK_ERR_NOT_SUPPORTED;
	}

	static PTEID_CallbackResult Control(PTEID_CardHandle handle, unsigned long ulControl, const unsigned char *cmdData,
										unsigned long cmdLength, unsigned char *respBuffer,
										unsigned long *respBufferSize, unsigned long ulMaxResponseSize, void *context) {
		return PTEID_CALLBACK_ERR_NOT_SUPPORTED;
	}
};

void dumpByteArray(const unsigned char *data, long length) {
	for (int i = 0; i != length; i++)
		printf("%02x", data[i]);
	puts("\n");
}

int main(int argc, char **argv) {
	SimplePCSCCallbacks pcsc_context;
	PTEID_CardInterfaceCallbacks callbacks = {
		.context = &pcsc_context,
		.establishContext = SimplePCSCCallbacks::EstablishContext,
		.releaseContext = SimplePCSCCallbacks::ReleaseContext,
		.listReaders = SimplePCSCCallbacks::ListReaders,
		.cardPresentInReader = SimplePCSCCallbacks::CardPresentInReader,
		.statusWithATR = SimplePCSCCallbacks::StatusWithATR,
		.connect = SimplePCSCCallbacks::Connect,
		.disconnect = SimplePCSCCallbacks::Disconnect,
		.transmit = SimplePCSCCallbacks::Transmit,
		.recover = SimplePCSCCallbacks::Recover,
		.control = SimplePCSCCallbacks::Control,
		.beginTransaction = SimplePCSCCallbacks::BeginTransaction,
		.endTransaction = SimplePCSCCallbacks::EndTransaction,
	};

    PTEID_ReaderSet::initSDKWithCallbacks(callbacks);

	try {
		PTEID_Config::SetTestMode(true);

		PTEID_ReaderContext &readerContext = ReaderSet.getReader();

		if (!readerContext.isCardPresent()) {
			std::cout << "No Card Present" << std::endl;
		}

		auto token = readerContext.getMultiPassToken();
		std::cout << "Read token size: " << token.Size() << std::endl;
		dumpByteArray(token.GetBytes(), token.Size());

	} catch (PTEID_ExNoReader &e) {
		std::cout << "No card reader found!" << std::endl;
		goto cleanup;
	} catch (PTEID_Exception &e) {
		std::stringstream sstream;
		sstream << std::hex << e.GetError();
		std::cout << "Caught exception in some SDK method. Error code: " << sstream.str().c_str();
		goto cleanup;
	}

	std::cout << "Successful test." << std::endl;
	PTEID_ReleaseSDK();
	exit(0);

cleanup:
	PTEID_ReleaseSDK();
	exit(1);
}
