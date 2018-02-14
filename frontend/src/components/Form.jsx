import React from 'react';
import request from 'superagent';

export default class Form extends React.Component {

    constructor(props){
        super(props);

        this.updateValue = this.updateValue.bind(this);
    }

    updateValue(){
        let bookingModel = {
            firstname : this.firstNameValue.value,
            lastname : this.lastNameValue.value,
            totalprice : this.priceValue.value,
            depositpaid : this.depositPaidValue.value,
            bookingdates : {
                checkin : this.checkInValue.value,
                checkout : this.checkOutValue.value
            },
            additionalneeds : this.additionalNeedsValue.value
        }

        request
            .post('/booking')
            .set('Content-Type', 'application/json')
            .send(bookingModel)
            .then((res, err) => {
                window.location.reload();
            }).catch(e => {
                console.log(e);
            })
    }

    render() {
        return (
            <div className="row form">
                <div className="col-md-1"><input ref={el => this.firstNameValue=el} type="text" id="firstname" className="form-control input-sm" placeholder="Firstname" /></div>
                <div className="col-md-2"><input ref={el => this.lastNameValue=el} type="text" id="lastname" className="form-control input-sm" placeholder="Lastname" /></div>
                <div className="col-md-1"><input ref={el => this.priceValue=el} type="text" id="totalprice" className="form-control input-sm" placeholder="Price" /></div>
                <div className="col-md-1"><input ref={el => this.depositPaidValue=el} type="text" id="depositpaid" className="form-control input-sm" placeholder="Deposit" /></div>
                <div className="col-md-2"><input ref={el => this.additionalNeedsValue=el} type="text" id="additionalneeds" className="form-control input-sm" placeholder="Additional" /></div>
                <div className="col-md-2"><input ref={el => this.checkInValue=el} type="text" id="checkin" className="form-control input-sm" placeholder="Checkin" /></div>
                <div className="col-md-2"><input ref={el => this.checkOutValue=el} type="text" id="checkout" className="form-control input-sm" placeholder="Checkout" /></div>
                <div className="col-md-1"><button onClick={this.updateValue} className="btn btn-sm addButton">Add</button></div>
            </div>
        )
    }

}