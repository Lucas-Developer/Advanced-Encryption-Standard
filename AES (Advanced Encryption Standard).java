import javax.crypto.spec.SecretKeySpec;
  import javax.crypto.spec.IvParameterSpec;
   
  import javax.crypto.Cipher;
   
  public class EncriptaDecriptaAES {
         
         static String IV = "AAAAAAAAAAAAAAAA";
         static String textopuro = "teste texto 12345678\0\0\0";
         static String chaveencriptacao = "0123456789abcdef";
   
         public static void main(String [] args) {
   
               try {
                      
                      System.out.println("Texto Puro: " + textopuro);
                       
                      byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);
                       
                      System.out.print("Texto Encriptado: ");
   
                      for (int i=0; i<textoencriptado.length; i++)
                             System.out.print(new Integer(textoencriptado[i])+" ");
                      
                      System.out.println("");
                       
                      String textodecriptado = decrypt(textoencriptado, chaveencriptacao);
                       
                      System.out.println("Texto Decriptado: " + textodecriptado);
                
               } catch (Exception e) {
                      e.printStackTrace();
               }
         }
   
         public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
               Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
               SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
               encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
               return encripta.doFinal(textopuro.getBytes("UTF-8"));
         }
   
         public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
               Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
               SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
               decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
               return new String(decripta.doFinal(textoencriptado),"UTF-8");
         }
         
  }

//-------------------------------------------------------------------------
//-------------------------------------------------------------------------

//Executando o algoritmo acima teremos como resultado a sa�da abaixo:

//Texto Puro: teste texto 12345678
//Texto Encriptado: 80 20 103 61 -80 -122 -15 -45 116 -53 73 -33 55 -58 -99 25 -106 124 -117 -18 37 -127 86 112 39 11 -60 -118 -125 -66 -111 96 
//Texto Decriptado: teste texto 12345678