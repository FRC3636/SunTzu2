package frc.robot.subsystems.flywheels

import com.revrobotics.CANSparkBase
import com.revrobotics.CANSparkFlex
import com.revrobotics.CANSparkLowLevel
import edu.wpi.first.math.util.Units
import edu.wpi.first.units.Distance
import edu.wpi.first.units.Measure
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.Velocity

class RealFlywheelIO {
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

    fun setVelocity(speed: Measure<Velocity<Distance>>) {
        val angluarSpeed = RadiansPerSecond.of(speed.baseUnitMagnitude() / FLYWHEEL_RADIUS.`in`(Meters))
        flywheelMotor.pidController.setReference(angluarSpeed.baseUnitMagnitude(), CANSparkBase.ControlType.kVelocity)
    }

    companion object Constants {
        val FLYWHEEL_RADIUS = Inches.of(1.5)
    }
}