/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neslerp;

/**
 *
 * @author DS
 */
public class BinData {
    private Integer serno;
    private String ship;
    private String dtg;
    private String itemRequested;
    private String qtyRequested;
    private String den;
    private String shipCurrentHolding;
    private String tankCapacity;
    private String depotHoldingAsAtDate;
    private String qty_recommended;
    private String estBalanceAfterApproval;
    private String qtyApproved;
    private String issuingDepot;
    private String locationOShip;
    private String remarks;

    public BinData(Integer serialNo, String ship, String dtg, String itemRequested, String qtyRequested, String den, String shipCurrentHolding, String tankCapacity, String depotHoldingAsAtDate, String qty_recommended, String estBalanceAfterApproval, String qtyApproved, String issuingDepot, String locationOShip, String remarks) {
        this.serno = serialNo;
        this.ship = ship;
        this.dtg = dtg;
        this.itemRequested = itemRequested;
        this.qtyRequested = qtyRequested;
        this.den = den;
        this.shipCurrentHolding = shipCurrentHolding;
        this.tankCapacity = tankCapacity;
        this.depotHoldingAsAtDate = depotHoldingAsAtDate;
        this.qty_recommended = qty_recommended;
        this.estBalanceAfterApproval = estBalanceAfterApproval;
        this.qtyApproved = qtyApproved;
        this.issuingDepot = issuingDepot;
        this.locationOShip = locationOShip;
        this.remarks = remarks;
    }

    public Integer getSerialNo() {
        return serno;
    }

    public String getShip() {
        return ship;
    }

    public String getDtg() {
        return dtg;
    }

    public String getItemRequested() {
        return itemRequested;
    }

    public String getQtyRequested() {
        return qtyRequested;
    }

    public String getDen() {
        return den;
    }

    public String getShipCurrentHolding() {
        return shipCurrentHolding;
    }

    public String getTankCapacity() {
        return tankCapacity;
    }

    public String getDepotHoldingAsAtDate() {
        return depotHoldingAsAtDate;
    }

    public String getQty_recommended() {
        return qty_recommended;
    }

    public String getEstBalanceAfterApproval() {
        return estBalanceAfterApproval;
    }

    public String getQtyApproved() {
        return qtyApproved;
    }

    public String getIssuingDepot() {
        return issuingDepot;
    }

    public String getLocationOShip() {
        return locationOShip;
    }

    public String getRemarks() {
        return remarks;
    }

    
}
