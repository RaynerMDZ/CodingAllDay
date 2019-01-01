import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncateText'
})
export class TruncateTextPipe implements PipeTransform {

  /**
   * Truncates the passed value to 300 characters.
   * @param value
   * @param args
   */
  transform(value: string, args?: any): any {

    // Strips HTML tags
    value = value.replace(/<(?:.|\n)*?>/gm, '');

    if (value.length >= 500) {
      return value.substring(0, 500) + '...';
    }
    return value;
  }

}
