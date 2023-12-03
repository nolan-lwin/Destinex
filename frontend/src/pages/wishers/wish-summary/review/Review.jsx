import React, { useState } from 'react';
import { Link } from "react-router-dom";
import './review.css';
import redRose from '../../../../assets/img/red-rose.png';

function Review() {

    const productName = sessionStorage.getItem("productName")
    const itemPriceLow = sessionStorage.getItem("itemPrice").split(",")[0]
    const itemPriceHigh = sessionStorage.getItem("itemPrice").split(",")[1]
    const date = sessionStorage.getItem("date")
    const name = sessionStorage.getItem("receiverFirstName") + " " + sessionStorage.getItem("receiverLastName")
    const phone = sessionStorage.getItem("receiverPhone")
    const email = sessionStorage.getItem("receiverEmail")
    const address = sessionStorage.getItem("receiverAddressString")


    return (
        <div className='review'>
            <div className="wishFormForSummary">
                <div className="wishFormForSummaryTitle">
                    <span className="wishFormForSummaryTitleText">Make a </span>
                    <span className="wishFormForSummaryTitleWish">Wish</span>
                </div>

                <div className='wishFormForSummaryContainer'>
                    <div className='step4'>
                        <span className='step4Text'>Step 4 of 4</span>
                    </div>

                    <div className='wishFormForSummarySubTitle'>
                        <span className='wishFormForSummarySubTitleText'>Please review items, shipping address, and make a payment:</span>
                    </div>
                </div>

                <div className='reviewContainer'>
                    <div className='reviewImage'>
                        <img className={"redRose"} src={redRose} alt={"Red-Rose.png"} />
                    </div>

                    <div className='reviewItemsAndInfo'>
                        <span className='name'><span className='boldName'>Name: </span>
                            {productName}
                        </span>
                        <span className='quantity'><span className='boldQuantity'>Quantity: </span>1</span>
                        <span className='priceRange'><span className='boldPriceRange'>Price Range: </span>
                            ${itemPriceLow} - ${itemPriceHigh}
                        </span>
                        <span className='arrivalDate'><span className='boldArrivalDate'>Arrival Date: </span>
                            {date}
                        </span>
                        <span className='recipientName'><span className='boldRecipientName'>Recipient's Name: </span>
                            {name}
                        </span>
                        <span className='recipientPhone'><span className='boldRecipientPhone'>Recipient's Phone Number: </span>
                            {phone}
                        </span>
                        <span className='recipientEmail'><span className='boldRecipientEmail'>Recipient's Email Address: </span>
                            {email}
                        </span>
                        <span className='recipientAddress'><span className='boldRecipientAddress'>Recipient's Shipping Address: </span>
                            {address}
                        </span>
                    </div>
                </div>

                <div className='lineBreak'>
                    <hr className='lineBreakForReview' />
                </div>
            </div>
        </div>
    )
}

export default Review;