package com.hsm.jvm.gc;

/**
 * 垃圾判定:
 *  根可达算法,根可以是 线程变量 静态变量 常量池 JNI指针
 * 垃圾回收算法:
 *  1. 标记-清除:位置不连续,容易产生内存碎片
 *  2. 拷贝:内存一分为二,没有碎片,但是浪费空间
 *  3. 标记压缩:将使用的内存放在一块,没有碎片,不浪费空间,但是效率偏低
 *
 * JVM 分代算法
 * new young
 *  存活对象少
 *  使用拷贝算法,效率高
 * old
 *  垃圾少
 *  一般使用标记压缩算法
 *  G1使用拷贝算法
 * 永久代(1.7)/元数据区(1.8)metaspace
 *  永久代,元数据-class
 *  永久代必须指定大小限制,元数据可以设置,也可以不设置,无上限(受限于物理内存)
 *  字符串常量 1.7 永久代 1.8 放在堆中
 *  MethodArea逻辑概念-永久代,元数据
 *
 *  堆内存逻辑分区
 *     8           1           1
 * eden(伊甸)    survivor    survivor    tenured
 *
 * 新生代= eden + 2个survivor(拷贝算法)
 *      YGC回收之后,大多数的对象会被回收
 *      年龄大了进入老年代(15,CMS 6)
 *      survivor区存不下直接进入老年代
 * 老年代
 *      顽固分子
 *      老年代满了就进行FullGC
 * GC Tuning(Generation)
 *      尽量减少FGC
 *      MinorGc = YGC
 *      MajorGc = FGC
 * 常见的垃圾回收机制
 *  1. serial 年轻代,串行回收
 *  2. parallel scavenge 并行回收
 *  3. ParNew 年轻代,配合CMS并行回收
 *  4. serialOld
 *  5. parallelOld
 *  6. Concurrent Mark Sweep 老年代,并发的,垃圾回收和应用程序同时运行,降低STW的时间
 *  7. G1
 *  8. ZGC
 *  9. Shenandoah
 *  10.Eplison
 *
 *  1.8 默认垃圾回收:parallel scavenge + parallelOld
 *
 *  JVM 参数分类
 *  标准 -开头 ,所有的hotspot都支持
 *  非标准 -x开头,特定版本的hotspot支持特定命令
 *  不稳定命令 -XX开头,下个版本可能取消
 *      java -XX:+PrintFlagsFinal
 *      java -XX:+PrintFlagsInitial
 *      java -XX:+PrintCommandLineFlags
 */
public class GcTest {

}
