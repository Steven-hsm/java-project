package com.hsm.spock

import spock.lang.Specification;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: MockSpec
 * @description: TODO
 * @date 2020/10/21 14:22
 */
class MockSpec extends Specification {
    Publisher publisher = new Publisher()
    def subscriber = Mock(Subscriber)
    def subscriber2 = Mock(Subscriber)

    def setup() {
        publisher.subscribers << subscriber // << 相当于 list.add()
        publisher.subscribers << subscriber2
    }

    def "should send messages to all subscribers"() {
        when:
        publisher.send("hello")

        then:
        1 * subscriber.receive("hello")
        1 * subscriber2.receive("hello")
    }

}

class Publisher {
    List<Subscriber> subscribers = []
    int messageCount = 0
    void send(String message){
        // *. 分别调用list每个元素的receive方法
        subscribers*.receive(message)
        messageCount++
    }
}

interface Subscriber {
    void receive(String message)
}
