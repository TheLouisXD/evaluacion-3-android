package com.example.evaluacion_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbManager extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "BotiquinDB";
    private static final int DATABASE_VERSION = 1;

    // definimos el nombre y las columnas de la tabla
    private static final String TABLE_MEDICAMENTOS = "medicamentos";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CANTIDAD = "cantidad";
    private static final String KEY_FECHA_VENCIMIENTO = "fecha_vencimiento";
    private static final String KEY_MILIGRAMOS = "miligramos";
    private static final String KEY_PRESENTACION = "presentacion";
    private static final String KEY_DESCRIPCION = "descripcion";

    // Creamos la tabla, creando un string con los datos que definimos para la tabla arriba
    private static final String CREATE_TABLE_MEDICAMENTOS =
            "CREATE TABLE " + TABLE_MEDICAMENTOS + "(" +
             KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
             KEY_NOMBRE + " TEXT, " +
             KEY_CANTIDAD + " INTEGER, " +
             KEY_FECHA_VENCIMIENTO + " TEXT, " +
             KEY_MILIGRAMOS + " INTEGER, " +
             KEY_PRESENTACION + " TEXT, " +
             KEY_DESCRIPCION + " TEXT)";

    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_MEDICAMENTOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEDICAMENTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_MEDICAMENTOS + "'");
        onCreate(db);
    }

    // Metodo para ingresar datos en la tabla
    public long addMedicamento(String nombre, int cantidad, String fechaVencimiento, int miligramos, String presentacion, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Ingresamos los datos en la tabla
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_CANTIDAD, cantidad);
        values.put(KEY_FECHA_VENCIMIENTO, fechaVencimiento);
        values.put(KEY_MILIGRAMOS, miligramos);
        values.put(KEY_PRESENTACION, presentacion);
        values.put(KEY_DESCRIPCION, descripcion);

        // insertamos los datos en la tabla
        long insert = db.insert(TABLE_MEDICAMENTOS, null, values);

        return insert;
    }

    public ArrayList<MedicamentoModel> getAllMedicamentos() {
        // creamos una lista de medicamentos
        ArrayList<MedicamentoModel> medicamentosArrayList = new ArrayList<MedicamentoModel>();
        String selectQuery = "SELECT * FROM " + TABLE_MEDICAMENTOS;
        // seleccionamos la base de datos
        SQLiteDatabase db = this.getReadableDatabase();
        // ejecutamos la consulta
        Cursor c = db.rawQuery(selectQuery, null);
        // recorremos el cursor para obtener los datos de la tabla
        if (c.moveToFirst()) {
            do {
                MedicamentoModel medicamento = new MedicamentoModel();
                medicamento.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                medicamento.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));
                medicamento.setCantidad(c.getInt(c.getColumnIndex(KEY_CANTIDAD)));
                medicamento.setFechaVencimiento(c.getString(c.getColumnIndex(KEY_FECHA_VENCIMIENTO)));
                medicamento.setMiligramos(c.getInt(c.getColumnIndex(KEY_MILIGRAMOS)));
                medicamento.setPresentacion(c.getString(c.getColumnIndex(KEY_PRESENTACION)));
                medicamento.setDescripcion(c.getString(c.getColumnIndex(KEY_DESCRIPCION)));
                // agregamos el medicamento a la lista
                medicamentosArrayList.add(medicamento);
            } while (c.moveToNext());
        }
        return medicamentosArrayList;
    }

    public int updateMedicamento(int id, String nombre, String cantidad, String fechaVencimiento, String miligramos, String presentacion, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creamos el contenedor de valores
        ContentValues values = new ContentValues();

        // Agregamos los valores a la tabla
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_CANTIDAD, cantidad);
        values.put(KEY_FECHA_VENCIMIENTO, fechaVencimiento);
        values.put(KEY_MILIGRAMOS, miligramos);
        values.put(KEY_PRESENTACION, presentacion);
        values.put(KEY_DESCRIPCION, descripcion);

        // Actualizamos la tabla
        return db.update(TABLE_MEDICAMENTOS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteMedicamento(int id) {

        // Eliminamos la fila de la tabla
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICAMENTOS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}




