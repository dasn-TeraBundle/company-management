import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule, MatIconModule, MatInputModule, MatSelectModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule, MatSelectModule,
    MatIconModule
  ],
  exports: [
    MatButtonModule,
    MatInputModule, MatSelectModule,
    MatIconModule
  ],
  declarations: []
})
export class BmmmaterialModule { }
