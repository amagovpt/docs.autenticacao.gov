import Foundation
import Security

/*
  Signing and certificate reading demo program for Cartão de Cidadão (Portugal eID card) using macOS Security framework
  The certificate and signing key should be found if there's a CC card inserted
  in a card reader. Both card generations are supported in current Autenticacao.gov software
*/


func signWithCCKey(inputData: Data) {
	/* Attribute kSecAttrLabel can have the following values for authentication and signature key respectively:
		CC_AUTH_KEY, CC_SIGNATURE_KEY
	 */
	let query = [
	    kSecClass : kSecClassKey,
	    kSecAttrLabel as String: "CC_SIGNATURE_KEY",
	    kSecReturnRef: true 
	] as NSDictionary

	var result: CFTypeRef?
	let status = SecItemCopyMatching(query, &result)

	if status == noErr {
	    let privateKey = result as! SecKey

	    let algorithm_rsa: SecKeyAlgorithm = .rsaSignatureMessagePKCS1v15SHA256
	    let algorithm_ec: SecKeyAlgorithm = .ecdsaSignatureMessageX962SHA256
	    let signature_algo = SecKeyIsAlgorithmSupported(privateKey, .sign, algorithm_rsa) ? algorithm_rsa : algorithm_ec

	    var error: Unmanaged<CFError>?
		guard let signature = SecKeyCreateSignature(privateKey,
                                            signature_algo,
                                            inputData as CFData,
                                            &error) as Data? else {
                                                print("Signature error, need to get the underlying CFError!")
                                                return }
        
        let signatureString = signature.base64EncodedString()
		print("CC signature: \(signatureString)")

	}
	else {
		print("Failed to find CC key!")
	}

}

func writeCertificateToFile(cfData: CFData) {
	let data = cfData as Data

	let filePath = "CC_signature.crt"
	let fileURL = URL(fileURLWithPath: filePath)

	do {
	    try data.write(to: fileURL)
	    print("Data successfully written to \(filePath)")
	} catch {
	    print("Failed to write data to file: \(error.localizedDescription)")
	}
}

func findCertificate() {
	
	/* Attribute kSecAttrLabel can have the following values for authentication and signature certificate respectively:
		"CC AUTH CERTIFICATE", "CC SIGNATURE CERTIFICATE"
	 */

	let query = [
	    kSecClass : kSecClassCertificate,
	    kSecAttrLabel as String: "CC SIGNATURE CERTIFICATE",
	    kSecReturnRef: true 
	] as NSDictionary

	var result: CFTypeRef?
	let status = SecItemCopyMatching(query, &result)
	
	if status == noErr {
	    let certificate = result as! SecCertificate
	    let summary = SecCertificateCopySubjectSummary(certificate)
		print("CC certificate found with subject: \(summary!)")

		let data = SecCertificateCopyData(certificate)
		writeCertificateToFile(cfData: data)

	}
}

let dataToBeSigned = Data("Input data for signature".utf8)
signWithCCKey(inputData: dataToBeSigned)
findCertificate()
