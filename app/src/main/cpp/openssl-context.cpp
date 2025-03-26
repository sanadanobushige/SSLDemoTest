#include <jni.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <openssl/x509.h>
#include <openssl/pem.h>

static SSL_CTX *ssl_ctx = NULL;
extern "C" JNIEXPORT jlong JNICALL
Java_com_example_ssldemo_openssl_OpenSSLContext_nativeCreateSSLContext(JNIEnv *env, jobject obj) {
    SSL_CTX *ctx;
    const SSL_METHOD *method;
    OpenSSL_add_all_algorithms();
    SSL_load_error_strings();
    method = TLS_client_method();
    ctx = SSL_CTX_new(method);
    if (!ctx) {
        ERR_print_errors_fp(stderr);
        return 0;
    }
    ssl_ctx = ctx;
    return (jlong) ctx;
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_ssldemo_openssl_OpenSSLContext_nativeFreeSSLContext(JNIEnv
                                                                     *env,
                                                                     jobject obj, jlong
                                                                     ctxPtr) {
    if (ctxPtr) {
        SSL_CTX *ctx = (SSL_CTX *) ctxPtr;
        SSL_CTX_free(ctx);
        ctx = NULL;
    }
}