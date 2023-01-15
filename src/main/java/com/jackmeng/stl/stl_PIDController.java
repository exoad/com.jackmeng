package com.jackmeng.stl;

public class stl_PIDController
{
    private final double P;
    private final double I;
    private final double D;
    private double setpoint;
    private double errorSum;
    private double lastError;
    private double maxErrorSum;
    private double maxOutput;
    private double minOutput;
    private double lastTime;
    private double dt;
    private double deadband;
    private boolean firstRun;
    private boolean isOnTarget;
    private double onTargetError;
    private int onTargetCount;
    private int onTargetCountMax;

    public stl_PIDController(double P, double I, double D)
    {
        this.P = P;
        this.I = I;
        this.D = D;
        errorSum = 0;
        lastError = 0;
        maxErrorSum = Double.MAX_VALUE;
        maxOutput = Double.MAX_VALUE;
        minOutput = -Double.MAX_VALUE;
        lastTime = System.nanoTime();
        dt = 0.01;
        deadband = 0;
        firstRun = true;
        isOnTarget = false;
        onTargetError = 0.1;
        onTargetCount = 0;
        onTargetCountMax = 10;
    }

    public void setSetpoint(double setpoint)
    {
        this.setpoint = setpoint;
    }

    public void setMaxErrorSum(double maxErrorSum)

    {
        this.maxErrorSum = maxErrorSum;
    }

    public void setMaxOutput(double maxOutput)

    {
        this.maxOutput = maxOutput;
    }

    public void setMinOutput(double minOutput)

    {
        this.minOutput = minOutput;
    }

    public void setDt(double dt)
    {
        this.dt = dt;
    }

    public void setDeadband(double deadband)

    {
        this.deadband = deadband;
    }

    public void setOnTargetError(double onTargetError)

    {
        this.onTargetError = onTargetError;
    }

    public void setOnTargetCountMax(int onTargetCountMax)

    {
        this.onTargetCountMax = onTargetCountMax;
    }

    public double calculate(double processVariable)
    {
        double time = System.nanoTime();
        if (firstRun)
        {
            dt = (time - lastTime) / 1e9;
            firstRun = false;
        }
        double error = setpoint - processVariable;
        if (Math.abs(error) < deadband)
            error = 0;
        errorSum += error * dt;
        errorSum = Math.min(errorSum, maxErrorSum);
        double dError = (error - lastError) / dt;
        double output = P * error + I * errorSum + D * dError;
        output = Math.max(Math.min(output, maxOutput), minOutput);
        lastError = error;
        lastTime = time;
        checkOnTarget(error);
        return output;
    }

    private void checkOnTarget(double error)
    {
        if (Math.abs(error) < onTargetError)
        {
            onTargetCount++;
            if (onTargetCount >= onTargetCountMax)
                isOnTarget = true;
        }
        else
        {
            isOnTarget = false;
            onTargetCount = 0;
        }
    }

    public boolean isOnTarget()
    {
        return isOnTarget;
    }

    public void reset()
    {
        errorSum = 0;
        lastError = 0;
        isOnTarget = false;
        onTargetCount = 0;
    }
}
