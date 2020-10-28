package com.hsm.spock

import com.hsm.spock.entity.User
import spock.lang.Specification
import spock.lang.Unroll

class LianXinSpec extends Specification {

    def syntax(){
        def person = new User(name: "Fred", age: 33, sex: "male")

        expect:
        with(person) {
            name == "Fred"
            age == 33
            sex == "male"
        }
    }
    




}
