package recipe.ch3.r5

import org.apache.commons.math3.complex.Complex

operator fun Complex.plus(c: Complex) = this.add(c)
operator fun Complex.plus(c: Double) = this.add(c)
operator fun Complex.minus(c: Complex) = this.subtract(c)
operator fun Complex.minus(c: Double) = this.subtract(c)
operator fun Complex.div(c: Complex) = this.divide(c)
operator fun Complex.div(c: Double) = this.divide(c)
operator fun Complex.times(c: Complex) = this.multiply(c)
operator fun Complex.times(c: Double) = this.multiply(c)
operator fun Complex.times(c: Int) = this.multiply(c)
operator fun Double.times(c: Complex) = c.multiply(this)
operator fun Complex.unaryMinus() = this.negate()