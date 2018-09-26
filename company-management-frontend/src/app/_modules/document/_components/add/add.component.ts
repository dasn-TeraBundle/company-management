import {Component, OnInit} from '@angular/core';
import {DocumentService} from "../../../../_services/document.service";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  constructor(private _docService: DocumentService) {
  }

  ngOnInit() {
  }

  onUpload(files: FileList) {
    const file = files.item(0);

    this._docService.add(file)
      .subscribe(res => alert("Uploaded Successfully"));
  }
}
