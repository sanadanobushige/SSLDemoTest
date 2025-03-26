cmake_minimum_required(VERSION 3.10.2)

add_library(native-openssl SHARED openssl-context.cpp openssl-socket.cpp)


set(OPENSSL_ROOT_DIR ${CMAKE_SOURCE_DIR}/openssl)

include_directories(${OPENSSL_ROOT_DIR}/include)

add_library(crypto STATIC IMPORTED)

set_target_properties(crypto PROPERTIES IMPORTED_LOCATION ${OPENSSL_ROOT_DIR}/lib/libcrypto.a)

add_library(ssl STATIC IMPORTED)

set_target_properties(ssl PROPERTIES IMPORTED_LOCATION ${OPENSSL_ROOT_DIR}/lib/libssl.a)

target_link_libraries(native-openssl ssl crypto log)