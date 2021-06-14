package test

enum class StudentStatus {
    NOT_ENROLLED,
    ACTIVE,
    GRADUATED
}

class Student(var status: StudentStatus)

fun main(args: Array<String>) {
    val student = Student(StudentStatus.NOT_ENROLLED)
}

fun studentMessage(status: StudentStatus): String {
    return when(status) {
        StudentStatus.NOT_ENROLLED -> "과정에 등록하세요!"
        StudentStatus.ACTIVE -> "환영합니다"
        StudentStatus.GRADUATED -> "졸업을 축하합니다."
    }
}