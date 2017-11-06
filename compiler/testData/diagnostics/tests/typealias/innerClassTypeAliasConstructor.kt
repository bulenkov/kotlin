// !WITH_NEW_INFERENCE
// NI_EXPECTED_FILE

class Outer {
    inner class Inner
}

typealias OI = Outer.Inner

fun test1(x: Outer) = x.OI()


class Generic<T> {
    inner class Inner
}

typealias GI<T> = Generic<T>.Inner
typealias GIntI = Generic<Int>.Inner

fun test2(x: Generic<Int>) = x.GI()
fun <T> test3(x: Generic<T>) = x.GI()
fun <T> test4(x: Generic<List<T>>) = x.GI()
fun <T> test5(x: Generic<T>) = <!TYPE_MISMATCH!>x<!>.<!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET!>GIntI<!><!>()
fun Generic<Int>.test6() = GIntI()