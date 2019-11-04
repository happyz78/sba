import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-calendar',
  templateUrl: './add-calendar.component.html',
  styleUrls: ['./add-calendar.component.css']
})
export class AddCalendarComponent implements OnInit {

  model = {};
  constructor(public dialogRef: MatDialogRef<AddCalendarComponent>) { }

  ngOnInit() {
  }

  save() {
    this.dialogRef.close(this.model);
  }

  close() {
      this.dialogRef.close();
  }
}
