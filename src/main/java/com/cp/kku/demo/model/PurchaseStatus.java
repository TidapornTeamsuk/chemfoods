package com.cp.kku.demo.model;

public enum PurchaseStatus {
    PENDING,     // รอการชำระเงิน (คำสั่งซื้อยังไม่ได้รับการชำระเงิน)
    PAID,        // ชำระเงินแล้ว (ลูกค้าชำระเงินแล้ว)
    COMPLETED,   // เสร็จสิ้น (คำสั่งซื้อถูกจัดส่งและเสร็จสิ้น)
    CANCELED     // ยกเลิก (คำสั่งซื้อถูกยกเลิก)
}
