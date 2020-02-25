import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
import {Router} from '@angular/router';
import { Vendor } from '../vendor';

@Component({
  selector: 'app-vendorinfo',
  templateUrl: './vendorinfo.component.html',
  styleUrls: ['./vendorinfo.component.css']
})
export class VendorinfoComponent implements OnInit {

  vendorName : string;
  venId : number;
  vendor : Vendor;
  constructor(private vendorService : VendorService) {
    this.vendorName = localStorage.getItem("user");
    this.vendorService.findByVendorName(this.vendorName).subscribe( x => {
       this.vendor=x;
       localStorage.setItem('vendor', JSON.stringify(this.vendor));
        // this.venId =x.vendorId;
     },
    err => {
      console.log(err);
    }) 
   }

  ngOnInit() {
    // this.custId=this.vendor.cusId;
    // alert(this.custId);
  }
}