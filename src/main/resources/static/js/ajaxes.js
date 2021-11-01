//获取前端RSA公钥密码、AES的key，并放到window
let genKeyPair = rsaUtil.genKeyPair();
window.jsPublicKey = genKeyPair.publicKey;
window.jsPrivateKey = genKeyPair.privateKey;

/**
 * 重写jquery的ajax方法
 */
let _ajax = $.ajax;//首先备份下jquery的ajax方法
$.ajax = function (opt) {
    //备份opt中error和success方法
    let fn = {
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (data, textStatus) {
        }
    };
    if (opt.error) {
        fn.error = opt.error;
    }
    if (opt.success) {
        fn.success = opt.success;
    }

    //加密再传输
    if (opt.type.toLowerCase() === "post") {
        let data = opt.data;
        //发送请求之前随机获取AES的key
        let aesKey = aesUtil.genKey();
        let encryption = window.jsPublicKey;//后端RSA公钥加密后的AES的key
        encryption = encryption.replace("-----BEGIN PUBLIC KEY-----","")
        encryption = encryption.replace("-----END PUBLIC KEY-----","")
        data = {
            data: aesUtil.encrypt(data, aesKey),//AES加密后的数据
            aesKey: rsaUtil.encrypt(aesKey, sessionStorage.getItem('javaPublicKey')),
            publicKey: encryption //前端公钥
        };
        opt.data = data;
    }

    //扩展增强处理
    let _opt = $.extend(opt, {
        //成功回调方法增强处理
        success: function (data, textStatus) {
            if (opt.type.toLowerCase() === "post") {
                if(data.data == -1){
                    data = {data:null,flag:false,msg:"请重新刷新页面或重新登录后再试！"}
                }else{
                    data = aesUtil.decrypt(data.data.data, rsaUtil.decrypt(data.data.aesKey, window.jsPrivateKey));
                }
            }
            //先获取明文aesKey，再用明文key去解密数据
            fn.success(data, textStatus);
        }
    });
    return _ajax(_opt);
};