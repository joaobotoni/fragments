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
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_xgp_manejo_melhoramento_form, parent, false);
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
        private final TextInputEditText notaInput;
        private final TextInputLayout layout;
        private TextWatcher currentTextWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCaracteristica = itemView.findViewById(R.id.nome_caracteristica);
            siglaCaracteristica = itemView.findViewById(R.id.sigla_caracteristica);
            notaInput = itemView.findViewById(R.id.nota);
            layout = itemView.findViewById(R.id.notaContainer);
            layout.setHintEnabled(false);
        }

        public void bind(FormsXgpManejoMelhoramentoComponent component) {
            if (currentTextWatcher != null) {
                notaInput.removeTextChangedListener(currentTextWatcher);
            }

            nomeCaracteristica.setText(component.getCaracteristica());
            siglaCaracteristica.setText(component.getSigla());

            if (component.isEhObservacao()) {
                notaInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                notaInput.setHint("Digite sua observação");
                notaInput.setText(component.getObservacao() != null ? component.getObservacao() : "");
                resetarEstilo();
                layout.setError(null);

                currentTextWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        component.setObservacao(s.toString().trim());
                        component.setNota(null);
                    }
                };
            } else {
                notaInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                notaInput.setHint("Digite a nota");
                notaInput.setText(component.getNota() != null ? String.valueOf(component.getNota()) : "");

                if (component.getNota() != null) {
                    validarNota(component.getNota(), component.getNotaInicial(), component.getNotaFinal());
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
                        try {
                            String text = s.toString().trim();
                            if (text.isEmpty()) {
                                component.setNota(null);
                                resetarEstilo();
                            } else {
                                int notaDigitada = Integer.parseInt(text);
                                component.setNota(notaDigitada);
                                validarNota(notaDigitada, component.getNotaInicial(), component.getNotaFinal());
                            }
                            component.setObservacao(null);
                        } catch (NumberFormatException e) {
                            component.setNota(null);
                            marcarComoInvalido("Nota inválida");
                        }
                    }
                };
            }
            notaInput.addTextChangedListener(currentTextWatcher);
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
