/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Android\\aidlclient\\src\\com\\example\\newleaning\\StudentService.aidl
 */
package com.example.newleaning;
public interface StudentService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.newleaning.StudentService
{
private static final java.lang.String DESCRIPTOR = "com.example.newleaning.StudentService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.newleaning.StudentService interface,
 * generating a proxy if needed.
 */
public static com.example.newleaning.StudentService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.newleaning.StudentService))) {
return ((com.example.newleaning.StudentService)iin);
}
return new com.example.newleaning.StudentService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getStudentById:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.example.newleaning.Student _result = this.getStudentById(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.newleaning.StudentService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.example.newleaning.Student getStudentById(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.example.newleaning.Student _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_getStudentById, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.example.newleaning.Student.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getStudentById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public com.example.newleaning.Student getStudentById(java.lang.String id) throws android.os.RemoteException;
}
