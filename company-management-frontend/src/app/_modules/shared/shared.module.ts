import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListPipe } from './../../_pipes/list.pipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ListPipe],
  exports: [
    ListPipe
  ]
})
export class SharedModule { }
