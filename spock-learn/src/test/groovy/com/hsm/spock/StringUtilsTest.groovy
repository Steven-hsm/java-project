package com.hsm.spock

import spock.lang.Specification

class StringUtilsTest extends Specification {
    void setup() {
    }

    void cleanup() {
    }

    def "字符串根据长度截取测试1"(){
        def strList = ConsumableauxStringUtils.splitByLength("abc123def",3)
        expect:
        assert strList.size() == 3
        assert strList[0] == "abc"
        assert strList[1] == "123"
        assert strList[2] == "def"
    }

    def "字符串根据长度截取测试2"(){
        def strList = ConsumableauxStringUtils.splitByLength("abc123def4",3)
        expect:
        assert strList.size() == 4
        assert strList[0] == "abc"
        assert strList[1] == "123"
        assert strList[2] == "def"
        assert strList[3] == "4"
    }

    def "字符串根据长度截取测试3"(){
        def strList = ConsumableauxStringUtils.splitByLength("abc123def45",3)
        expect:
        assert strList.size() == 4
        assert strList[0] == "abc"
        assert strList[1] == "123"
        assert strList[2] == "def"
        assert strList[3] == "45"
    }

    def "IsEmpty"() {
    }

    def "IsNotEmpty"() {
    }
}
