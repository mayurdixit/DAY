export class EquipmentInfo {
    private id;
    private name;
    private serialModelNumber;
    private inUse;
    private purchasedOn;
    private ownedBy;
    private usedSince;
    private storedAt;
    private kendraId;
    private comment;
    private updatedBy;
    private contact1_name;
    private contact1_phone;
    private contact1_email;
    private contact2_name;
    private contact2_phone;
    private contact2_email;
    private createdOn;

    getId(){ return this.id; }
    setId(id: number){ this.id = id; }
    getName(){ return this.name; }
    setName(name: string){ this.name = name; }
    getSerialModelNumber(){ return this.serialModelNumber; }
    setSerialModelNumber(serialNumber: string){ this.serialModelNumber = serialNumber; }
    getInUse(){ return this.inUse; }
    setInUse(inUse: boolean){ this.inUse = inUse; }
    getPurchasedOn(){ return this.purchasedOn; }
    setPurchasedOn(purchasedOn: Date){ this.purchasedOn = purchasedOn; }
    getOwnedBy(){ return this.ownedBy; }
    setOwnedBy(ownedBy: string){ this.ownedBy = ownedBy; }
    getUsedSince(){ return this.purchasedOn; }
    setUsedSince(usedSince: Date){ this.usedSince = usedSince; }
    getStoredAt(){ return this.storedAt; }
    setStoredAt(storedAt: string){ this.storedAt = storedAt; }
    getKendraId(){ return this.kendraId; }
    setKendraId(id: number){ this.kendraId = id; }
    getComment(){ return this.comment; }
    setComment(comment: string){ this.comment = comment; }
    getUpdatedBy(){ return this.updatedBy; }
    setUpdatedBy(updatedBy: number){ this.updatedBy = updatedBy; }
    getContact1_name(){ return this.contact1_name; }
    setContact1_name(name: string){ this.contact1_name= name; }
    getContact1_phone(){ return this.contact1_phone; }
    setContact1_phone(phone: string){ this.contact1_email = phone; }
    getContact1_email(){ return this.contact1_email; }
    setContact1_email(email: string){ this.contact1_email = email; }
    getContact2_name(){ return this.contact2_name; }
    setContact2_name(name: string){ this.contact2_name = name; }
    getContact2_phone(){ return this.contact2_phone; }
    setContact2_phone(phone: string){ this.contact2_phone = phone; }
    getContact2_email(){ this.contact2_email; }
    setContact2_email(email: string){ this.contact2_email = email; }
    getCreatedOn(){ return this.createdOn; }
    setCreatedOn(createdOn: Date) { this.createdOn = createdOn; }
}
