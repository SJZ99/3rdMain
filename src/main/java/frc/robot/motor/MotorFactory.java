/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.motor;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Config motor, using "Fluent Interface".
 * Must using all method before using configLikePrevious()
 */
public class MotorFactory {
    /**
     * Restoring motor config.
     */
    private static class MotorConfig {
        public static FeedbackDevice sensor;
        public static int sensorPosition;
        public static int pidSlot;
        public static int timeoutMs;
        public static int slotIdx;
    }
    /**
     * Initializing motor
     * @param motor
     * @return motor
     */
    public static TalonFX init(final TalonFX motor) {
        motor.configFactoryDefault();
        return motor;
    }
    /**
     * Initializing motor
     * @param Victor_motor
     * @return motor
     */
    public static WPI_VictorSPX init(final WPI_VictorSPX Victor_motor) {
        Victor_motor.configFactoryDefault();
        return Victor_motor;
    }

    /**
     * Set follower.And initializing motor.
     * 
     * @param master
     * @param follower
     * @return master
     */
    public static TalonFX setFollower(final TalonFX master, final TalonFX follower) {
        MotorFactory.init(master);
        MotorFactory.init(follower);

        follower.follow(master);
        return master;
    }
     /**
     * Set follower.And initializing motor.
     * 
     * @param Victor_master
     * @param Victor_follower
     * @return master
     */
    public static WPI_VictorSPX setFollower(final WPI_VictorSPX Victor_master, final WPI_VictorSPX Victor_follower) {
        MotorFactory.init(Victor_master);
        MotorFactory.init(Victor_follower);

        Victor_follower.follow(Victor_master);
        return Victor_master;
    }

    /**
     * Set sensor type
     * 
     * @param motor
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static TalonFX setSensor(final TalonFX motor, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        motor.configSelectedFeedbackSensor(sensorType);
        return motor;
    }
    /**
     * Set sensor type
     * 
     * @param motor
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static WPI_VictorSPX setSensor(final WPI_VictorSPX motor, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        motor.configSelectedFeedbackSensor(sensorType);
        return motor;
    }

    /**
     * Set sensor position.
     * 
     * @param motor
     * @param sensorPosition
     * @param pidSlot
     * @param timeoutMs
     * @return motor
     */
    public static TalonFX setPosion(final TalonFX motor, final int sensorPosition, final int pidSlot,
            final int timeoutMs) {
        MotorConfig.sensorPosition = sensorPosition;
        MotorConfig.pidSlot = pidSlot;
        MotorConfig.timeoutMs = timeoutMs;
        motor.setSelectedSensorPosition(sensorPosition, pidSlot, timeoutMs);
        return motor;
    }

    /**
     * Set sensor phase.
     * 
     * @param motor
     * @param phase
     * @return motor
     */
    public static TalonFX setSensorPhase(final TalonFX motor, final boolean phase) {
        motor.setSensorPhase(phase);
        return motor;
    }

    /**
     * Set invertType
     * 
     * @param motor
     * @param invertType
     * @return motor
     */
    public static TalonFX setInvert(final TalonFX motor, final InvertType invertType) {
        motor.setInverted(invertType);
        return motor;
    }
    /**
     * Set invertType
     * @param motor
     * @param invert
     * @return motor
     */
    public static TalonFX setInvert(final TalonFX motor, boolean invert) {
        motor.setInverted(invert);
        return motor;
    }

    /**
     * Set motor like previous.
     * 
     * @param motor 
     * @param sensorPhase
     * @param invertType  {@link MotorConfig}
     */
    public static void configLikePrevious(final TalonFX motor, final boolean sensorPhase, final InvertType invertType) {
        motor.configSelectedFeedbackSensor(MotorConfig.sensor);
        motor.setSelectedSensorPosition(MotorConfig.sensorPosition, MotorConfig.pidSlot, MotorConfig.timeoutMs);
        motor.setSensorPhase(sensorPhase);
        motor.setInverted(invertType);
    }
    public static void configLikePrevious(final TalonFX motor, final boolean sensorPhase, final boolean invert) {
        motor.configSelectedFeedbackSensor(MotorConfig.sensor);
        motor.setSelectedSensorPosition(MotorConfig.sensorPosition, MotorConfig.pidSlot, MotorConfig.timeoutMs);
        motor.setSensorPhase(sensorPhase);
        motor.setInverted(invert);
    }

    /**
     * set motor PID 設置馬達PID
     * 
     * @param motor 設置馬達
     * @param kP kP值(大約為5分之一kF) 調越大 對誤差的調整更靈敏
     * @param kF kF值 大約為1023(talon滿輸出)/全速運轉的速度單位(以falcon來說大約為21600) 
     * @param slotIdx 閉迴控制位置(0,1,2)
     */
    public static TalonFX configPID(final TalonFX motor,double kP,double kF,int slotIdx){
        motor.config_kP(slotIdx, kP);
        motor.config_kF(slotIdx, kF);
        return motor;
    }

    /**
     * set motor limit 設置馬達限制
     * @param deadband 設置死區
     * @param percentOut 設置百分比輸出
     * @param percentOut2 
     * @param Ramp 設置完全加速時間
     * @param timeoutMs 設置報錯時間
     */
    public static TalonFX configmotorlimit(final TalonFX motor,double deadband,double percentOut,double percentOut2,double Ramp,int timeoutMs){
        motor.configFactoryDefault();
        motor.configNeutralDeadband(deadband);
        motor.configPeakOutputForward(percentOut,timeoutMs);
        motor.configPeakOutputReverse(percentOut2,timeoutMs);
        motor.configClosedloopRamp(Ramp);
        return motor;
    }
}
