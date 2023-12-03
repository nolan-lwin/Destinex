import React, { useState } from 'react';
import { Link } from "react-router-dom";
import './order_summary.css';

function OrderSummary() {

    const itemPriceLow = sessionStorage.getItem("itemPrice").split(",")[0]
    const itemPriceHigh = sessionStorage.getItem("itemPrice").split(",")[1]
    const beforeTaxPriceLow = (parseInt(itemPriceLow) + 15).toString()
    const beforeTaxPriceHigh = (parseInt(itemPriceHigh) + 15).toString()
    const taxPrice = ((parseInt(itemPriceLow) + parseInt(itemPriceHigh))/2 * 0.05).toFixed(2).toString()
    const totalPriceLow = (parseInt(beforeTaxPriceLow) + parseInt(taxPrice)).toString()
    const totalPriceHigh = (parseInt(beforeTaxPriceHigh) + parseInt(taxPrice)).toString()

    return (
        <div className="orderSummary">

            <div className="orderSummaryContainer">

                <div className='orderSummarySubTitle'>
                    <span className='orderSummarySubTitleText'>Order Summary:</span>
                </div>

                <div className='itemsAndShippingCost'>
                    <div className='items'>
                        <span className='boldItems'>Items: </span>
                        ${itemPriceLow} - ${itemPriceHigh}
                    </div>
                    <div className='shippingCost'>
                        <span className='boldShippingCost'>Shipping & Handling: </span>
                        $15
                    </div>
                </div>

                <div className='lineBreak'>
                    <hr className='lineBreakForItemsAndShippingCost' />
                </div>

                <div className='totalBeforeTaxAndEstimatedTaxCollected'>
                    <div className='totalBeforeTax'>
                        <span className='boldTotalBeforeTax'>Total before tax: </span>
                        ${beforeTaxPriceLow} - ${beforeTaxPriceHigh}
                    </div>
                    <div className='estimatedTaxCollected'>
                        <span className='boldEstimatedTaxCollected'>Estimated tax to be collected: </span>
                        ${taxPrice}
                    </div>
                </div>

                <div className='lineBreak'>
                    <hr className='lineBreakForTotalBeforeTaxAndEstimatedTaxCollected' />
                </div>

                <div className='orderTotal'>
                    <div className='orderTotalText'>
                        <span className='boldOrderTotalText'>Order Total: </span>
                        ${totalPriceLow} - ${totalPriceHigh}
                    </div>
                </div>

            </div>

            <div className='lineBreak'>
                <hr className='lineBreakForOrderSummary' />
            </div>

        </div>
    )
}

export default OrderSummary;