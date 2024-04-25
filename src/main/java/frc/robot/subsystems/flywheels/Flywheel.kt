package frc.robot.subsystems.flywheels

import edu.wpi.first.units.Units.MetersPerSecond
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem

object Flywheel: Subsystem {
    var io: FlywheelIO = SimFlywheelIO()

    fun shoot(): Command {
        return this.runEnd({
            io.setVelocity(MetersPerSecond.of(1.0))
        }, {
            io.setVelocity(MetersPerSecond.zero())
        })
    }
}
