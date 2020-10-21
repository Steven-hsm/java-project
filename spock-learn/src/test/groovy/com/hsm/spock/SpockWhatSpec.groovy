package com.hsm.spock

import com.hsm.spock.entity.User
import spock.lang.Specification
import spock.lang.Unroll

class SpockWhatSpec extends Specification {
    def "maximum of two numbers"(int a, int b, int c) {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        1 | 3 | 3
        7 | 4 | 7
        0 | 0 | 0
    }

    def "maximum of two numbers2"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
    }

    @Unroll
    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
    }

    def user = Mock(User);
    def "mockTest"(){
        given:"user不能为空"
        assert user != null
        print(user.age)
        print(user.name)
    }



}
