import React from 'react';
import request from 'superagent';

export default class Booking extends React.Component {

    constructor() {
        super();
        this.state = {
            edit : false, 
            booking : {
                "firstname": null,
                "lastname": null,
                "totalprice": null,
                "depositpaid": null,
                "additionalneeds": null,
                "bookingdates": {
                    "checkin": null,
                    "checkout": null
                }
            }
        };

        this.destroyMe = this.destroyMe.bind(this);
        this.editMe = this.editMe.bind(this);
        this.updateValue = this.updateValue.bind(this);
    }

    componentDidMount() {
        if (process.env.NODE_ENV === 'test') return
        
        request
            .get('/booking/' + this.props.id)
            .then((res, err) => {
              const booking = res.body;
              this.setState({booking});
            }).catch(e => {
                console.log(e);
            })
    }

    destroyMe() {
        request
            .delete('/booking/' + this.props.id)
            .auth('admin', 'password123')
            .then((res, err) => {
                window.location.reload();
            }).catch(e => {
                console.log(e);
            })
    }

    editMe() {
        if(this.state.edit){
            this.setState({edit: false});
        } else {
            this.setState({edit: true});
        }
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
            .put('/booking/' + this.props.id)
            .set('Content-Type', 'application/json')
            .auth('admin', 'password123')
            .send(bookingModel)
            .then((res, err) => {
                window.location.reload();
            }).catch(e => {
                console.log(e);
            })
    }

    render() {
        if(this.state.edit){
            return(
                <div className="row">
                    <div className="col-md-1"><input ref={el => this.firstNameValue=el} defaultValue={this.state.booking.firstname} type="text" className="editfirstname form-control input-sm" placeholder="Firstname" /></div>
                    <div className="col-md-2"><input ref={el => this.lastNameValue=el} defaultValue={this.state.booking.lastname} type="text" className="editlastname form-control input-sm" placeholder="Lastname" /></div>
                    <div className="col-md-1"><input ref={el => this.priceValue=el} defaultValue={this.state.booking.totalprice} type="text" className="edittotalprice form-control input-sm" placeholder="Price" /></div>
                    <div className="col-md-1"><input ref={el => this.depositPaidValue=el} defaultValue={String(this.state.booking.depositpaid)} type="text" className="editdepositpaid form-control input-sm" placeholder="Deposit?" /></div>
                    <div className="col-md-2"><input ref={el => this.additionalNeedsValue=el} defaultValue={this.state.booking.additionalneeds} type="text" className="editadditionalneeds form-control input-sm" placeholder="Additional" /></div>
                    <div className="col-md-2"><input ref={el => this.checkInValue=el} defaultValue={this.state.booking.bookingdates.checkin} type="text" className="editcheckin form-control input-sm" placeholder="Checkin" /></div>
                    <div className="col-md-2"><input ref={el => this.checkOutValue=el} defaultValue={this.state.booking.bookingdates.checkout} type="text" className="editcheckout form-control input-sm" placeholder="Checkout" /></div>
                    <div className="col-md-1">
                        <button onClick={this.updateValue} className="edit btn btn-sm">Add</button>
                        <button onClick={this.editMe} className="cancel btn btn-sm"><i className="glyphicon glyphicon-remove"></i></button>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="row">
                    <div className="col-md-1">{this.state.booking.firstname}</div>
                    <div className="col-md-2">{this.state.booking.lastname}</div>
                    <div className="col-md-1">{this.state.booking.totalprice}</div>
                    <div className="col-md-1">{String(this.state.booking.depositpaid)}</div>
                    <div className="col-md-2">{this.state.booking.additionalneeds}</div>
                    <div className="col-md-2">{this.state.booking.bookingdates.checkin}</div>
                    <div className="col-md-2">{this.state.booking.bookingdates.checkout}</div>
                    <div className="col-md-1">
                        <button onClick={this.editMe} className="edit"><i className="glyphicon glyphicon-edit"></i></button>
                        <button onClick={this.destroyMe} className="delete"><i className="glyphicon glyphicon-remove"></i></button>
                    </div>      
                </div>
            );
        }
      }

}