//普通字符串
let text = "jiajiajia";

//秘钥对
let keyPair = rsaUtil.genKeyPair();
//公钥加密
let ciphertext = rsaUtil.encrypt(text,keyPair.publicKey);
//私钥解密
let plaintext = rsaUtil.decrypt(ciphertext,keyPair.privateKey);

console.log("秘钥：");console.log(keyPair.privateKey);
console.log("公钥：" + keyPair.publicKey);
console.log("加密前：" + text);
console.log("公钥加密后：" + ciphertext);
console.log("解密后：" + plaintext);