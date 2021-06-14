package test

sealed class StudentStatus {
    object NotEnrolled: StudentStatus()
    class Active(val courseId: String) : StudentStatus()
    object Graduated: StudentStatus()
}

class Student(var status: StudentStatus)

fun main(args: Array<String>) {
    val student = Student(StudentStatus.NotEnrolled)
    println(studentMessage(student.status))
}

fun studentMessage(status: StudentStatus): String {
    return when(status) {
        is StudentStatus.NotEnrolled -> "과정에 등록하세요!"
        is StudentStatus.Active -> "${status.courseId} 과정에 등록되었습니다."
        is StudentStatus.Graduated -> "졸업을 축하합니다."
    }
}