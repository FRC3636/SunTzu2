package subsystems.intake

import edu.wpi.first.units.Units
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.subsystems.Intake.RealIntakeIO
import frc.robot.subsystems.flywheels.Flywheel

object Intake: Subsystem {
    var io = RealIntakeIO()
    fun intake(): Command {
        return this.runEnd({
            io.setVelocity(Units.MetersPerSecond.of(1.0))
        }, {
            io.setVelocity(Units.MetersPerSecond.zero())
        })
    }
}