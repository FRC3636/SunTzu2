package frc.robot.subsystems.flywheels

import com.revrobotics.CANSparkBase
import com.revrobotics.CANSparkFlex
import com.revrobotics.CANSparkLowLevel
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.util.Units
import edu.wpi.first.units.Distance
import edu.wpi.first.units.Measure
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.Velocity
import edu.wpi.first.wpilibj.simulation.FlywheelSim

interface FlywheelIO {
    var leftSpeed: Double
    var rightSpeed: Double
    fun setVelocity(speed: Measure <Velocity<Distance>>)
    fun getVelocity(): Measure<Velocity<Distance>>
}

class RealFlywheelIO : FlywheelIO {
    val flywheelMotor = CANSparkFlex(11, CANSparkLowLevel.MotorType.kBrushless).apply {
        pidController.apply {
            p = 1.0
            i = 0.0
            d = 0.0
        }

        encoder.apply {
            positionConversionFactor = Units.rotationsToRadians(1.0)
            velocityConversionFactor = Units.rotationsPerMinuteToRadiansPerSecond(1.0)
        }
    }

    override fun setVelocity(speed: Measure<Velocity<Distance>>) {
        val angluarSpeed = RadiansPerSecond.of(speed.baseUnitMagnitude() / FLYWHEEL_RADIUS.`in`(Meters))
        flywheelMotor.pidController.setReference(angluarSpeed.baseUnitMagnitude(), CANSparkBase.ControlType.kVelocity)
    }

    override fun getVelocity(): Measure<Velocity<Distance>> {
        return MetersPerSecond.of(flywheelMotor.encoder.velocity * FLYWHEEL_RADIUS.`in`(Meters))
    }

    companion object Constants {
        val FLYWHEEL_RADIUS = Inches.of(1.5)
    }
}

class SimFlywheelIO: FlywheelIO {

    private val leftFlywheel = FlywheelSim(DCMotor.getNeoVortex(1), 1.0, 0.01)
    private val rightFlywheel = FlywheelSim(DCMotor.getNeoVortex(1), 1.0, 0.01)
    override fun setVelocity(speed: Measure<Velocity<Distance>>) {
        leftFlywheel.
    }

    override fun getVelocity(): Measure<Velocity<Distance>> {
        return MetersPerSecond.of(leftFlywheel.angularVelocityRadPerSec * FLYWHEEL_RADIUS.`in`(Meters))
        return MetersPerSecond.of(rightFlywheel.angularVelocityRadPerSec * FLYWHEEL_RADIUS.`in`(Meters))

    }

    companion object Constants {
        val FLYWHEEL_RADIUS = Inches.of(1.5)
    }
}
