using System;
using System.IO;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace CNG_test
{
    class Program
    {
        private const string ECC_PUBLIC_KEY_OID = "1.2.840.10045.2.1"; //public key eliptic curve

        static bool signAndVerifyElipticCurve(string sig_input, X509Certificate2 cert)
        {
            byte[] cng_signature = null;
            using (ECDsa rsa = cert.GetECDsaPrivateKey())
            {
                ECDsaCng ecdCng = rsa as ECDsaCng;

                if (ecdCng != null)
                {
                    try
                    {
                        //injectPIN(rsaCng);
                        cng_signature = ecdCng.SignData(Encoding.UTF8.GetBytes(sig_input));
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in RSA.SignData() ! Message: " + e.Message);
                        return false;
                    }

                    try
                    {
                        return ecdCng.VerifyData(Encoding.UTF8.GetBytes(sig_input), cng_signature);
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in RSA.VerifyData() ! Message: " + e.Message);
                        return false;
                    }
                }
                else
                {
                    TextWriter errorWriter = Console.Error;
                    errorWriter.WriteLine("Eliptic Curve key object is not an instance of ECDsaCng: certificate registration issue!");
                    errorWriter.WriteLine("This usually means that signature with this key is only available through legacy CryptoAPI");
                    return false;
                }
            }
        }
        static bool signAndVerify(string sig_input, HashAlgorithmName hash_algo, RSASignaturePadding padding_mode, X509Certificate2 cert)
        {
            byte[] cng_signature = null;
            using (RSA rsa = cert.GetRSAPrivateKey())
            {
                RSACng rsaCng = rsa as RSACng;

                if (rsaCng != null)
                {
                    try
                    {
                        //injectPIN(rsaCng);
                        cng_signature = rsaCng.SignData(Encoding.UTF8.GetBytes(sig_input), hash_algo, padding_mode);
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in RSA.SignData() ! Message: " + e.Message);
                        return false;
                    }

                    try
                    {
                        return rsaCng.VerifyData(Encoding.UTF8.GetBytes(sig_input), cng_signature, hash_algo, padding_mode);
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in RSA.VerifyData() ! Message: " + e.Message);
                        return false;
                    }
                }
                else
                {
                    TextWriter errorWriter = Console.Error;
                    errorWriter.WriteLine("RSA key object is not an instance of RSACng: certificate registration issue!");
                    errorWriter.WriteLine("This usually means that signature with this key is only available through legacy CryptoAPI");
                    return false;
                }
            }
        }
               

        static void Main(string[] args)
        {

            Console.WriteLine(".net CNG Signature Test");

            const string sig_input = "Hello, World!";

            X509Store store = new X509Store(StoreName.My, StoreLocation.CurrentUser);
            store.Open(OpenFlags.ReadOnly);

            string issuerName = "EC de Assinatura Digital Qualificada do Cartão de Cidadão";
            X509Certificate2Collection collection = store.Certificates.Find(X509FindType.FindByIssuerName, issuerName, false); //

            if (collection.Count == 0)
            {
                Console.WriteLine("Error - No matching certificate found in Windows cert store!");
                return;
            }

            X509Certificate2 cert = collection[0];
            bool isEliptic = false;
            string algo = cert.GetKeyAlgorithm();
            if (algo == ECC_PUBLIC_KEY_OID)
                isEliptic = true;
            Console.WriteLine("Algo certificate: " + algo);
            //cert.Get
            Console.WriteLine("Signing with Windows certificate: " + cert.Subject);

            if(isEliptic)
            {
                Console.WriteLine("┌────────────────────────────────────┐");
                Console.WriteLine("├──── Eliptic Curve signature tests  ────┤");

                Console.WriteLine("[SHA-256] Verified: " + signAndVerifyElipticCurve(sig_input, cert));
            }
            else
            {
                Console.WriteLine("┌────────────────────────────────────┐");
                Console.WriteLine("├──── RSA-PKCS1 signature tests  ────┤");

                Console.WriteLine("[SHA-256] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1, cert));
                Console.WriteLine("[SHA-384] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA384, RSASignaturePadding.Pkcs1, cert));
                Console.WriteLine("[SHA-512] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA512, RSASignaturePadding.Pkcs1, cert));

                Console.WriteLine("┌────────────────────────────────────────────────────────────────────────┐");
                Console.WriteLine("├──── RSA-PSS signature tests - an updated minidriver is needed!! ───────┤");

                Console.WriteLine("[SHA-256] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA256, RSASignaturePadding.Pss, cert));
                Console.WriteLine("[SHA-384] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA384, RSASignaturePadding.Pss, cert));
                Console.WriteLine("[SHA-512] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA512, RSASignaturePadding.Pss, cert));
            }
        }
    }
   
}
