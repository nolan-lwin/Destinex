import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import 'react-phone-number-input/style.css';
import PhoneInput from 'react-phone-number-input';
import './signup_form_granter.css';
import {CountryDropdown, RegionDropdown} from "react-country-region-selector";
import axios from "axios";

function SignupForm () {
    const [phone, setPhone] = useState('');
    const [granterFirstAddress, setGranterFirstAddress] = useState('');
    const [granterSecondAddress, setGranterSecondAddress] = useState('');
    const [granterCountry, setGranterCountry] = useState('');
    const [granterRegion, setGranterRegion] = useState('');
    const [granterCity, setGranterCity] = useState('');
    const [granterZip, setGranterZip] = useState('');
    const [granterAddress, setGranterAddress] = useState('');
    const [granterAddressPoint, setGranterAddressPoint] = useState('');

    const navigate = useNavigate();


    const getCoordinates = async (address) => {
        const url = `https://nominatim.openstreetmap.org/search.php?q=${address}&format=jsonv2`;

        try {
            const response = await axios.get(url);
            console.log('response',response.data)
            if (response.data[0]) {
                const lat = response.data[0].lat;
                const lon = response.data[0].lon;
                return { lat, lon };
            } else {
                return null;
            }
        } catch (error) {
            console.error('Geocoding error:', error);
            return null;
        }
    };

    const handleAddressChange = async (event) => {
        // Might not need this until last page if using sessionStorage

        event.preventDefault();

        // Gets coordinates based on first address
        const coords = await getCoordinates(granterFirstAddress + ", " + granterCity);

        if (coords) {
            const geoJsonPoint = {
                type: "Point",
                coordinates: [coords.lon, coords.lat], // Note: Longitude and Latitude order
            };

            setGranterAddressPoint(geoJsonPoint);

            sessionStorage.setItem("granterAddressPoint", JSON.stringify(geoJsonPoint.coordinates));
            console.log('sessionstorage', sessionStorage.getItem("granterAddressPoint"))
        }

        const adrs = {
            FirstAddress: granterFirstAddress,
            SecondAddress: granterSecondAddress,
            City: granterCity,
            Region: granterRegion,
            Country: granterCountry,
            Zip: granterZip,
        };

        const adrsJSON = JSON.stringify(adrs);

        sessionStorage.setItem("granterAddress", adrsJSON);

        navigate("/granter/setup")
    }

    return (
        <div className='signupForm'>

            <div className='signupFormTitle'>
                <span className='signupFormTitleText'>Sign Up to Become a </span>
                <span className='signupFormTitleDestinex'>Granter</span>
            </div>

            <div className='signupFormInput'>
                <div className='firstAddress'>
                    <label className='firstAddressText'>Your Address</label>
                    <input className='firstAddressInput' type='text'
                           onChange={(val) => setGranterFirstAddress(val.target.value)}
                    />
                </div>

                <div className='secondAddress'>
                    <label className='secondAddressText'>Apartment, suite, etc.</label>
                    <input className='secondAddressInput' type='text'
                           onChange={(val) => setGranterSecondAddress(val.target.value)}
                    />
                </div>


                <div className='recipientCountry'>
                    <label className='recipientCountryText'>Country</label>
                    <CountryDropdown
                        value={granterCountry}
                        onChange={(val) => setGranterCountry(val)}
                        id="country"
                        classes="dropdownClass" // apply your custom styles here
                    />
                </div>

                <div className='recipientState'>
                    <label className='recipientStateText'>State</label>
                    <RegionDropdown
                        country={granterCountry}
                        value={granterRegion}
                        onChange={(val) => setGranterRegion(val)}
                        id="state"
                        classes="dropdownClass" // apply your custom styles here
                        disableWhenEmpty={true}
                    />
                </div>

                <div className='cityAndZipCode'>
                    <div className='recipientCity'>
                        <label className='recipientCityText'>City</label>
                        <input className='recipientCityInput' type='text'
                               onChange={(val) => setGranterCity(val.target.value)}
                        />
                    </div>

                    <div className='recipientZipCode'>
                        <label className='recipientZipCodeText'>ZIP/Postal Code</label>
                        <input className='recipientZipCodeInput' type='text'
                               onChange={(val) => setGranterZip(val.target.value)}
                        />
                    </div>
                </div>

                <div className='contactNumber'>
                    <label className='contactNumberText'>Contact Number</label>
                    <div className="phoneInputContainer">
                        <PhoneInput
                            placeholder="Enter your contact number"
                            value={phone}
                            onChange={setPhone}
                            international
                            defaultCountry="US"
                        />
                    </div>
                </div>

                <div className='automatedSMSText'>
                    <span>
                        Destinex sends automated SMS messages for informational purposes and to provide the service.
                    </span>
                </div>

                <div className='agreement'>
                    <input type='checkbox'/>
                    <span className='agreementText'>
                        Keep me up to date on special offers and other news via SMS messages sent using an autodialer. Consent is not a condition of signing up as a granter.
                    </span>
                </div>

                <div className='stopSMSText'>
                    <span>
                    </span>
                </div>

                <Link className='signupButtonText' onClick={handleAddressChange}>
                    Sign Up
                </Link>
            </div>

        </div>
    )
}

export default SignupForm;