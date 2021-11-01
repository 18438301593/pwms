//字符串
let text = "jijaia";

//key
let genKey = aesUtil.genKey();
//key加密
let ciphertext = aesUtil.encrypt(text,genKey);
//key解密
let plaintext = aesUtil.decrypt(ciphertext,genKey);

console.log("key：");console.log(genKey);
console.log("加密前：");console.log(text);
console.log("key加密后：" + ciphertext);
console.log("key解密后：");console.log(plaintext);