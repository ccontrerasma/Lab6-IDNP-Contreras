package com.example.uicomponente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RoomInfoFragment extends DialogFragment {
    private static final String ARG_ROOM_NAME = "ROOM_NAME";

    public static RoomInfoFragment newInstance(String roomName) {
        RoomInfoFragment fragment = new RoomInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROOM_NAME, roomName);
        fragment.setArguments(args);
        return fragment;
    }

    public RoomInfoFragment() {
        // constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Quitar el título por defecto del Dialog (opcional)
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        View view = inflater.inflate(R.layout.fragment_room_info, container, false);

        String roomName = getArguments() != null ? getArguments().getString(ARG_ROOM_NAME, "Ambiente") : "Ambiente";

        TextView titleView = view.findViewById(R.id.roomTitle);
        TextView descriptionView = view.findViewById(R.id.roomDescription);
        ImageView imageView = view.findViewById(R.id.roomImage);
        Button btnClose = view.findViewById(R.id.btnClose);

        RoomInfo roomInfo = getRoomInfo(roomName);

        titleView.setText(roomInfo.getTitle());
        descriptionView.setText(roomInfo.getDescription());
        imageView.setImageResource(roomInfo.getImageResId());

        // Cerrar el diálogo con el botón
        btnClose.setOnClickListener(v -> dismiss());

        // Permitir cerrar tocando fuera (opcional)
        setCancelable(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Ajustar tamaño del dialog si se desea (ej: ancho = MATCH_PARENT)
        if (getDialog() != null && getDialog().getWindow() != null) {
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90); // 90% ancho
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private RoomInfo getRoomInfo(String roomName) {
        switch (roomName) {
            case "Patio":
                return new RoomInfo(
                        "Patio",
                        "Sección de la infraestructura donde se puede descansar y apreciar la arquitectura.",
                        R.drawable.patio
                );
            case "Sala Principal":
                return new RoomInfo(
                        "Sala Principal",
                        "Sala principal de la infraestructura, destinada a reuniones y eventos.",
                        R.drawable.sala_principal
                );
            case "Capilla":
                return new RoomInfo(
                        "Capilla",
                        "Capilla ubicada en la parte baja izquierda de la infraestructura, con un ambiente de recogimiento.",
                        R.drawable.capilla
                );
            case "Altar":
                return new RoomInfo(
                        "Altar",
                        "Altar principal donde se realizan las ceremonias.",
                        R.drawable.altar
                );
            default:
                return new RoomInfo(
                        "Información no disponible",
                        "No hay información sobre esta sección.",
                        R.drawable.capilla

                );
        }
    }
}

