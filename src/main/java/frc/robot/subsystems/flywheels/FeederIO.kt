package frc.robot.subsystems.flywheels

import com.revrobotics.CANSparkFlex

package com.frcteam3636.frc2024.subsystems.shooter

import com.frcteam3636.frc2024.CANSparkFlex
import com.frcteam3636.frc2024.REVMotorControllerId
import com.frcteam3636.frc2024.utils.math.TAU
import com.revrobotics.CANSparkLowLevel
import edu.wpi.first.units.Angle
import edu.wpi.first.units.Measure
import edu.wpi.first.units.Units.RadiansPerSecond
import edu.wpi.first.units.Velocity
import edu.wpi.first.units.Voltage
import org.littletonrobotics.junction.LogTable
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.inputs.LoggableInputs

interface FeederIO {
    class Inputs : LoggableInputs {
        var velocity: Measure<Velocity<Angle>> = RadiansPerSecond.zero()
        override fun toLog(table: LogTable?) {
            table?.put("velocity", velocity)
        }

        override fun fromLog(table: LogTable) {
            velocity = table.get("velocity", velocity)
        }
    }

    fun updateInputs(inputs: Inputs)
    fun setIndexerVoltage(voltage: Measure<Voltage>)
}

class RealFeederIO: FeederIO {
    private val indexer = CANSparkFlex(REVMotorControllerId.Indexer, CANSparkLowLevel.MotorType.kBrushless).apply{
        pidController.apply{
            p = 1.0
            i = 0.0
            d = 0.0
        }
    }
}