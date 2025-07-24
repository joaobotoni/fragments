package com.app.fragments.ui.adapter;

import android.view.View;
import android.text.Editable;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.text.InputType;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;

import java.util.List;

public class FormsXgpManejoMelhoramentoAdapter extends RecyclerView.Adapter<FormsXgpManejoMelhoramentoAdapter.ViewHolder> {

    private final Context context;
    private final List<FormsXgpManejoMelhoramentoComponent> components;

    public FormsXgpManejoMelhoramentoAdapter(Context context, List<FormsXgpManejoMelhoramentoComponent> components) {
        this.context = context;
        this.components = components;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_xgp_manejo_melhoramento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FormsXgpManejoMelhoramentoComponent component = components.get(position);
        holder.bind(component);
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public List<FormsXgpManejoMelhoramentoComponent> getComponents() {
        return components;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomeCaracteristica;
        private final TextView siglaCaracteristica;
        private final TextInputEditText valorInput;
        private final TextInputLayout layout;
        private TextWatcher currentTextWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCaracteristica = itemView.findViewById(R.id.nome_caracteristica);
            siglaCaracteristica = itemView.findViewById(R.id.sigla_caracteristica);
            valorInput = itemView.findViewById(R.id.valor);
            layout = itemView.findViewById(R.id.container);
            layout.setHintEnabled(false);
        }

        public void bind(FormsXgpManejoMelhoramentoComponent component) {
            if (currentTextWatcher != null) {
                valorInput.removeTextChangedListener(currentTextWatcher);
            }

            nomeCaracteristica.setText(component.getCaracteristica());
            siglaCaracteristica.setText(component.getSigla());

            if (component.isEhObservacao()) {
                valorInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                valorInput.setHint("Digite sua observação");
                valorInput.setText(component.getValorDigitado() != null ? component.getValorDigitado() : ""); // Usa valorDigitado
                resetarEstilo();
                layout.setError(null);

                currentTextWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        component.setValorDigitado(s.toString().trim());
                    }
                };
            } else {
                valorInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                valorInput.setHint("Digite a nota");
                valorInput.setText(component.getValorDigitado() != null ? component.getValorDigitado() : ""); // Usa valorDigitado

                if (component.getValorDigitado() != null && !component.getValorDigitado().isEmpty()) {
                    try {
                        int nota = Integer.parseInt(component.getValorDigitado());
                        validarNota(nota, component.getNotaInicial(), component.getNotaFinal());
                    } catch (NumberFormatException e) {
                        marcarComoInvalido("Nota inválida");
                    }
                } else {
                    resetarEstilo();
                }

                currentTextWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        String text = s.toString().trim();
                        component.setValorDigitado(text);

                        if (text.isEmpty()) {
                            resetarEstilo();
                        } else {
                            try {
                                int notaDigitada = Integer.parseInt(text);
                                validarNota(notaDigitada, component.getNotaInicial(), component.getNotaFinal());
                            } catch (NumberFormatException e) {
                                marcarComoInvalido("Nota inválida");
                            }
                        }
                    }
                };
            }
            valorInput.addTextChangedListener(currentTextWatcher);
        }

        private void validarNota(int nota, Integer notaInicial, Integer notaFinal) {
            if (notaInicial == null || notaFinal == null) {
                marcarComoInvalido("Limites de nota não definidos.");
                return;
            }

            if (nota >= notaInicial && nota <= notaFinal) {
                marcarComoValido();
            } else {
                marcarComoInvalido("Nota deve ser entre " + notaInicial + " e " + notaFinal);
            }
        }

        private void marcarComoValido() {
            layout.setBoxStrokeColor(ContextCompat.getColor(itemView.getContext(), R.color.colorValid));
            layout.setBoxStrokeWidth(2);
            layout.setError(null);
        }

        private void marcarComoInvalido(String errorMessage) {
            layout.setBoxStrokeColor(ContextCompat.getColor(itemView.getContext(), R.color.colorError));
            layout.setBoxStrokeWidth(3);
            layout.setError(errorMessage);
        }

        private void resetarEstilo() {
            layout.setBoxStrokeColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
            layout.setBoxStrokeWidth(1);
            layout.setError(null);
        }
    }
}
