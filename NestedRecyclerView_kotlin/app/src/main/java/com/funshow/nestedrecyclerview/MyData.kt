package com.funshow.nestedrecyclerview

class MyData(val title: String, val nesData: List<NestedData>) {
    class NestedData(val nesTitle: String)
}