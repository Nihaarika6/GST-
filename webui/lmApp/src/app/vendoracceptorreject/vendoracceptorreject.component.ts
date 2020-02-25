import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-vendoracceptorreject',
  templateUrl: './vendoracceptorreject.component.html',
  styleUrls: ['./vendoracceptorreject.component.css']
})
export class VendoracceptorrejectComponent implements OnInit {

  vendId : number;
  orderId : number;
  status : string;
  result : string;

  constructor(private _vendorService : VendorService) { 
    this.orderId=parseInt(localStorage.getItem("acceptorrejectOrderId"));
    this.vendId =parseInt(localStorage.getItem("acceptorrejectVendId"));
  }
  acceptOrReject() {
    this._vendorService.acceptOrReject(this.orderId,this.vendId,this.status).subscribe(x => {
      this.result=x;
    })
  }

  ngOnInit() {
  }

}

