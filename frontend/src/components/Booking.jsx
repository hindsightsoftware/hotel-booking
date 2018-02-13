import React from 'react';
import request from 'superagent';

export default class Booking extends React.Component {

    constructor() {
        super();
        this.state = { 
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

    render() {
        return (
            <div className="row">
                <div className="col-md-1">{this.state.booking.firstname}</div>
                <div className="col-md-2">{this.state.booking.lastname}</div>
                <div className="col-md-1">{this.state.booking.totalprice}</div>
                <div className="col-md-1">{String(this.state.booking.depositpaid)}</div>
                <div className="col-md-2">{this.state.booking.additionalneeds}</div>
                <div className="col-md-2">{this.state.booking.bookingdates.checkin}</div>
                <div className="col-md-2">{this.state.booking.bookingdates.checkin}</div>
                <div className="col-md-1"><i className="glyphicon glyphicon-edit"></i> <i className="icon-remove-circle"></i></div>      
            </div>
        );
      }

}