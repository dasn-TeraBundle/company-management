import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'list'
})
export class ListPipe implements PipeTransform {

  transform(items: any, srchText?: any): any {
	  if (srchText == null) return items;
	  
	  return items.filter(item => item.toLowerCase().indexOf(srchText) > -1);
  }

}
