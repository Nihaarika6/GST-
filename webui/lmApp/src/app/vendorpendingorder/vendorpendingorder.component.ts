import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
//import { Router } from '@angular/router';
import { Vendor } from '../vendor';
import { ActivatedRoute, Router } from '@angular/router';
// import { Orders } from '../orders';

@Component({
  selector: 'app-vendorpendingorder',
  templateUrl: './vendorpendingorder.component.html',
  styleUrls: ['./vendorpendingorder.component.css']
})
export class VendorpendingorderComponent implements OnInit {
 

  vendorName : string;
  vendorId : number;
  vendor : Vendor;
  orders : Observable<Vendor[]>;
  selValue : number;
  values : string;
  filter : string[];
  feature = 'Feature 1'
  arr : [];
  rs : string;
  status : string;
  selectedFeatures: any = [];

  acceptOrReject(orderId,vendId) {
    localStorage.setItem("acceptorrejectOrderId",orderId);
    localStorage.setItem("acceptorrejectVendId",vendId);
  this._router.navigate(['../vendoracceptorreject'], {relativeTo: this._route});
  }
  constructor(private vendorService : VendorService, private _route : ActivatedRoute, private _router :Router) {
    this.vendor = localStorage.getItem('vendor')? JSON.parse(localStorage.getItem('vendor')):[];
    //  alert("Vendor Id is " +this.vendor.vendorId);
    this.orders=this.vendorService.pendingVendorOrders(this.vendor.vendorId);
     
   }
   checkIfAllSelected(x) {
    this.selectedFeatures.push(x);
    // alert("added");
  }
  AcceptMe() {
    this.values = this.selectedFeatures.toString();
    // alert(this.values);
    this.filter = this.values.split(",");
    this.status="ACCEPTED";
    for(var v=0;v<this.filter.length;v++) {
      alert(this.filter[v]);
      this.vendorService.acceptOrReject(parseInt(this.filter[v]),this.vendor.vendorId,this.status).subscribe(x => {
        this.rs=x;
      })
    }
    alert("Selected Orders Accepted Successfully...");
    this._router.navigate(['/vendordashboard']);
    // alert(this.customer.customerId);

  }
  ngOnInit() {
   
  }

}