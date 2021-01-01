cmake_minimum_required(VERSION 3.10.2)
add_library(
        cpp_lib

        SHARED

        cpp_lib.cpp)
find_library(
        cpp_lib

        log)
target_link_libraries(
        cpp_lib

        ${log-lib})