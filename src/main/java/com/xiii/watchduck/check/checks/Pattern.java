//package com.xiii.watchduck.check.checks;
//
//import com.xiii.watchduck.check.Category;
//import com.xiii.watchduck.check.Check;
//import com.xiii.watchduck.check.CheckInfo;
//import com.xiii.watchduck.exempt.ExemptType;
//import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
//
//@CheckInfo(name = "Check CheckType", category = Category.MOVEMENT)
//public class CheckCheckType extends Check {
//
//    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
//        boolean exempt = isExempt(ExemptType.FLYING);
//
//        //if(code > 0 && !exempt) fail("Flag Reason, eg: Jumped too high", "Debug value, eg: 0.43);
//    }
//}
