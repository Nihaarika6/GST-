  
import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
//import { Router } from '@angular/router';
import { Vendor } from '../vendor';
// import { Orders } from '../orders';

@Component({
  selector: 'app-vendororderhistory',
  templateUrl: './vendororderhistory.component.html',
  styleUrls: ['./vendororderhistory.component.css']
})
export class VendororderhistoryComponent implements OnInit {

  vendorName : string;
  vendorId : number;
  vendor : Vendor;
  orders : Observable<Vendor[]>;
  constructor(private vendorService : VendorService) {
    this.vendor = localStorage.getItem('vendor')? JSON.parse(localStorage.getItem('vendor')):[];
    // alert("Vendor Id is " +this.vendor.cusId);
    this.orders=this.vendorService.orderHistory(this.vendor.vendorId);
     
   }
  ngOnInit() {
   
  }

}