cmake_minimum_required(VERSION 3.5)
project(password_cracker)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Encryptor.h Key.h Key.cpp)
add_executable(password_cracker ${SOURCE_FILES})