import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<PaymentComponent>) { }

  ngOnInit() {
  }

  save() {
    this.dialogRef.close({});
  }

  close() {
      this.dialogRef.close();
  }

}
