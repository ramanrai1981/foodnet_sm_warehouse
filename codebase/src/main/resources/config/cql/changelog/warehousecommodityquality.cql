CREATE TABLE warehousecommodityquality (
    lotid uuid,
    qualityparam text,
    qualityvalue text,
    PRIMARY KEY (lotid,qualityparam)
)
