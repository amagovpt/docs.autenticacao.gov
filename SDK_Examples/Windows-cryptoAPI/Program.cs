using System;
using System.IO;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace CNG_test
{
    class CryptoAPISigner
    {
        private const string ECC_PUBLIC_KEY_OID = "1.2.840.10045.2.1"; //Elliptic curve public key

        static bool signAndVerifyEllipticCurve(string sig_input, HashAlgorithmName hash_algo, X509Certificate2 cert)
        {
            byte[] ecdsa_signature = null;
            using (ECDsa ec_key = cert.GetECDsaPrivateKey())
            {

                if (ec_key != null)
                {
                    try
                    {
                        ecdsa_signature = ec_key.SignData(Encoding.UTF8.GetBytes(sig_input), hash_algo);
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in ECDsa.SignData()! Message: " + e.Message);
                        return false;
                    }

                    try
                    {
                        return ec_key.VerifyData(Encoding.UTF8.GetBytes(sig_input), ecdsa_signature, hash_algo);
                    }
                    catch (CryptographicException e)
                    {
                        TextWriter errorWriter = Console.Error;
                        errorWriter.WriteLine("Exception in ECDsa.VerifyData()! Message: " + e.Message);
                        return false;
                    }
                }
                else
                {
                    TextWriter errorWriter = Console.Error;
                    errorWriter.WriteLine("Failed to get ECDSA key object! This certificate has probably an innapropriate key type.");
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
            const string sig_input = "Hello, World!";
            Console.WriteLine(".net CNG Signature Test");           

            X509Store store = new X509Store(StoreName.My, StoreLocation.CurrentUser);
            store.Open(OpenFlags.ReadOnly);
               
            //Change this to sign with authentication certificate
            string issuerName = "EC de Assinatura Digital Qualificada do Cartão de Cidadão";
            X509Certificate2Collection collection = store.Certificates.Find(X509FindType.FindByIssuerName, issuerName, false);

            if (collection.Count == 0)
            {
                Console.WriteLine("Error - No matching certificate found in Windows cert store!");
                return;
            }

            X509Certificate2 cert = collection[0];
            string algo = cert.GetKeyAlgorithm();
            
            Console.WriteLine("Signing with Windows certificate: " + cert.Subject);
            Console.WriteLine("Certificate key algorithm: " + algo);

            if (algo == ECC_PUBLIC_KEY_OID)
            {
                Console.WriteLine("┌────────────────────────────────────┐");
                Console.WriteLine("├──── ECDSA signature test  ─────────┤");

                Console.WriteLine("[SHA-256] Verified: " + signAndVerifyEllipticCurve(sig_input, HashAlgorithmName.SHA256, cert));
            }
            else
            {
                Console.WriteLine("┌────────────────────────────────────┐");
                Console.WriteLine("├──── RSA-PKCS#1 signature test  ────┤");

                Console.WriteLine("[SHA-256] Verified: " + signAndVerify(sig_input, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1, cert));
            }
        }
    }
   
}
