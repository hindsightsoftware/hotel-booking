import React from 'react';
import { shallow, mount, render } from 'enzyme';
import renderer from 'react-test-renderer';

import App from '../src/components/App.jsx';

test('App', () => {

    const testBookings = [
        {
            "id": 1
        },{
            "id": 2
        }
    ]

    const component = renderer.create(
        <App bookings={testBookings} />
    );
       
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();

});